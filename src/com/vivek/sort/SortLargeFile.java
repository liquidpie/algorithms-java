package com.vivek.sort;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Sort a file with huge volume of data given memory constraint
 *
 * Solution:
 * - External sorting
 * - Basically, you sort small chunks of data first, write it back to the disk and then iterate over those to sort all.
 *
 * For example, for sorting 900 megabytes of data using only 100 megabytes of RAM:
 *
 *     1. Read 100 MB of the data in main memory and sort by some conventional sorting method
 *        (you should use in-place sorting algorithm, like QuickSort, HeapSort).
 *     2. Write the sorted data to disk.
 *     3. Repeat steps 1 and 2 until all the data is in sorted 100 MB chunks (there are 900MB / 100MB = 9 chunks),
 *        which now need to be merged into one single output file.
 *     4. Read the first 10 MB of each sorted chunk (of 100 MB) into input buffers in main memory and allocate the
 *        remaining 10 MB for an output buffer. (In practice, it might provide better performance to make the output buffer
 *        larger and the input buffers slightly smaller.)
 *     5. Perform a 9-way merge and store the result in the output buffer. Whenever the output buffer fills, write it
 *        to the final sorted file and empty it. Whenever any of the 9 input buffers empties, fill it with the next 10 MB
 *        of its associated 100 MB sorted chunk until no more data from the chunk is available. This is the key step that
 *        makes external merge sort work externally -- because the merge algorithm only makes one pass sequentially through
 *        each of the chunks, each chunk does not have to be loaded completely; rather, sequential parts of the chunk
 *        can be loaded as needed.
 */
public class SortLargeFile {

    public static void main(String[] args) throws IOException {
        Path fullPath = new File("./data", "large.txt").toPath();
        List<File> files = ExternalSort.splitAndSortChunks(fullPath.toAbsolutePath().toString(), "D:/temp", 25,
                new StringComparator());
        ExternalSort.mergeSortedChunks(files, "D:/LargeFile.txt", new StringComparator());

    }

    static class ExternalSort {

        /**
         * split the large file into several sorted temp files
         */
        public static List<File> splitAndSortChunks(final String fileName, final String tempDirectory,
                                                    final int noOfSplits, final StringComparator cmp) throws IOException {
            List<File> files = new ArrayList<>();
            RandomAccessFile raf = new RandomAccessFile(fileName, "r");
            long sourceSize = raf.length();
            long bytesPerSplit = sourceSize / noOfSplits;
            long remainingBytes = sourceSize % noOfSplits;
            int maxReadBufferSize = 8 * 1024; // 8KB
            int fileCounter = 1;
            for (int i = 1; i <= noOfSplits; i++) {
                File dir = new File(tempDirectory);
                if (dir.exists()) {
                    dir.delete();
                }
                dir.mkdir();
                File file = new File(tempDirectory + "/temp-file-" + fileCounter + ".txt");
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                if (bytesPerSplit > maxReadBufferSize) {
                    long numReads = bytesPerSplit / maxReadBufferSize;
                    long numRemainingRead = bytesPerSplit % maxReadBufferSize;
                    for (int j = 0; j < numReads; j++) {
                        readWrite(raf, bos, maxReadBufferSize);
                    }
                    if (numRemainingRead > 0) {
                        readWrite(raf, bos, numRemainingRead);
                    }
                } else {
                    readWrite(raf, bos, bytesPerSplit);
                }
                sortFileContent(file, cmp);
                files.add(file);
                fileCounter++;
                bos.close();
            }
            if (remainingBytes > 0) {
                File file = new File(tempDirectory + "/temp-file-" + fileCounter + ".txt");
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                readWrite(raf, bos, remainingBytes);
                sortFileContent(file, cmp);
                files.add(file);
                bos.close();
            }
            return files;
        }
        private static void readWrite(RandomAccessFile raf, BufferedOutputStream bos, long numBytes) throws IOException {
            byte[] buf = new byte[(int) numBytes];
            int val = raf.read(buf);
            if (val != -1) {
                bos.write(buf);
                bos.flush();
            }
        }
        /**
         * Sort file content
         */
        private static void sortFileContent(File file, StringComparator cmp) throws IOException {
            List<String> lines = new ArrayList<>();
            try (Stream<String> ln = Files.lines(file.toPath())) {
                lines = ln.collect(Collectors.toList());
            }
            lines.sort(cmp);
            try (BufferedWriter bw = Files.newBufferedWriter(file.toPath())) {
                for (String line : lines) {
                    bw.write(line);
                    bw.write("\r\n");
                }
            }
        }

        /**
         * Merge all sorted files into a big file in a sorted manner
         */
        public static void mergeSortedChunks(final List<File> files, final String outputFile, final StringComparator cmp)
                throws IOException {
            List<BufferedReader> brReaders = new ArrayList<>();
            PriorityQueue<PQEntry> heap = new PriorityQueue<>((e1, e2) -> cmp.compare(e1.line, e2.line));

            File f = new File(outputFile);
            if (f.exists()) {
                f.delete();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile, true));
            try {
                for (File file : files) {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    brReaders.add(br);
                    String line = br.readLine();
                    heap.add(new PQEntry(line, br));
                }
                while (!heap.isEmpty()) {
                    PQEntry nextToGo = heap.poll();
                    bw.write(nextToGo.line);
                    bw.write("\r\n");
                    String line = nextToGo.br.readLine();
                    if (line != null) {
                        heap.add(new PQEntry(line, nextToGo.br));
                    }
                }
            } finally {
                for (BufferedReader br : brReaders) {
                    br.close();
                }
                File dir = files.get(0).getParentFile();
                for (File file : files) {
                    file.delete();
                }
                if (dir.exists()) {
                    dir.delete();
                }
                bw.close();
            }
        }

    }

    public static class StringComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }
    }

    private static class PQEntry {
        String line;
        BufferedReader br;

        public PQEntry(String line, BufferedReader br) {
            this.line = line;
            this.br = br;
        }
    }

}
