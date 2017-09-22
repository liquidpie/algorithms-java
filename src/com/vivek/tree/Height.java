package com.vivek.tree;

public class Height {

	int height(Node root)
	{
		if (root == null) {
			return -1;
		}
		int lHeight = height(root.left);
		int rHeight = height(root.right);

		if (lHeight > rHeight) {
			return lHeight + 1;
		}
		else {
			return rHeight + 1;
		}
	}

}
