package TreeTopic;


public class RedBlackTree {

	private static final boolean RED=false;
	private static final boolean BLACK=true;

	private TNode root;


	public TNode getRoot() {
		return root;
	}



	public void setRoot(TNode root) {
		this.root = root;
	}


	private TNode parentOf(TNode node) {
		return node==null?null:node.parent;
	}
	private TNode leftOf(TNode node) {
		return node==null?null:node.left;
	}
	private TNode rightOf(TNode node) {
		return node==null?null:node.right;
	}

	/**
	 * 为空返回黑色，不为空返回节点的颜色
	 * @return color of node
	 */
	private boolean colorOf(TNode node) {
		return node==null?BLACK:node.color;
	}

	private void setColor(TNode node,boolean color) {
		if(node!=null)
			node.color=color;
	}



	/**
	 * 围绕p左旋
	 *
	 * 		 pp				 pp
	 * 		/				 /
	 *     p				pr(x)
	 *    / \			   /  \
	 *  pl   pr(x)	=>    p    rr
	 * 		/  \ 		 / \
	 * 	   rl  rr		pl  rl
	 */
	private void leftRotate(TNode p) {
		//p为空
		if(p==null) return;

		//p不为空，开始左旋
		TNode x=p.right;
		p.right=x.left;
		if(x.left!=null) {
			x.left.parent=p;
		}

		x.parent=p.parent;
		if(p.parent==null) {
			root=x;
		}
		else if(p==p.parent.left){
			p.parent.left=x;
		}
		else {
			p.parent.right=x;
		}

		x.left=p;
		p.parent=x;
	}

	/**
	 * 围绕p右旋
	 *
	 * 			  pp				pp
	 * 		      /					/
	 *    		 p				   pl(x)
	 *   	    / \				  / \
	 *     (x)pl   pr      =>   ll   p
	 * 		 / \					/ \
	 * 	 	ll  lr                 lr  pr
	 *
	 *
	 * @param p
	 */
	private void rightRotate(TNode p) {
		//p为空
		if(p==null) return;

		//p不为空，开始左旋
		TNode x=p.left;
		p.left=x.right;
		if(x.right!=null) {
			x.right.parent=p;
		}

		x.parent=p.parent;
		if(p.parent==null) {
			root=x;
		}
		else if(p==p.parent.left){
			p.parent.left=x;
		}
		else {
			p.parent.right=x;
		}

		x.right=p;
		p.parent=x;
	}

	/**
	 * 插入节点
	 * @param _data
	 */
	public void put(int _data) {
		TNode t=this.root;
		//如果是根节点
		if(t == null) {
			this.root=new TNode(_data);
			return;
		}

		//寻找插入位置
		//定义一个双亲指针
		TNode parent;
		do {
			parent=t;
			if(_data<t.data) {
				t=t.left;
			}
			else if(_data>t.data) {
				t=t.right;
			}
			else {
				//不允许存在同样值的节点
				return;
			}
		}while(t!=null);

		//现在t在插入位置,parent为插入位置的双亲节点
		TNode e=new TNode(parent,_data);
		//如果插入位置最终落在左子树，则直接将双亲节点左指针指向e
		if(parent.data>_data) {
			parent.left=e;
		}
		//如果插入位置最终落在右子树，则直接将双亲节点右指针指向e
		else {
			parent.right=e;
		}
		e.parent=parent;

		//调整
		fixAfterPut(e);
	}

	/**
	 * 1.p挂在黑色节点下，不需调整。
	 * 2.左三，右三才要调整，即黑红红
	 */
	private void fixAfterPut(TNode p) {
		//新增一定是红色
		p.color=RED;
		//p挂在黑色节点下，不需调整
		while(p!=root&&p!=null&&p.parent.color==RED) {
			//p的父节点是p爷节点的左孩子
			if(parentOf(p)==leftOf(parentOf(parentOf(p)))) {
				//叔叔节点
				TNode uncleNode=rightOf(parentOf(parentOf(p)));
				//叔节点为红
				if(colorOf(uncleNode)==RED) {
					//有叔叔节点为红色，所以父节点和叔叔节点变黑，爷节点变红
					setColor(parentOf(p), BLACK);
					setColor(uncleNode, BLACK);
					setColor(parentOf(parentOf(p)), RED);
					//继续调整爷节点
					p=parentOf(parentOf(p));

				}
				//叔节点为黑
				else {
					//如果p是父节点的右孩子，还需要先左旋一下，使p的父节点下去
					// 局部为三角调整为直线
					if(p==rightOf(parentOf(p))) {
						p=parentOf(p);
						leftRotate(p);
					}
					//局部为直线
					//父节点变黑，爷节点变红，以爷节点右旋转
					setColor(parentOf(p), BLACK);
					setColor(parentOf(parentOf(p)), RED);
					rightRotate(parentOf(parentOf(p)));
				}
			}

			//右三,p的父节点是p爷节点的右孩子
			else {
				//叔叔节点
				TNode uncleNode=leftOf(parentOf(parentOf(p)));
				if(colorOf(uncleNode)==RED) {
					//有叔叔节点，所以父节点和叔叔节点变黑，爷节点变红
					setColor(parentOf(p), BLACK);
					setColor(uncleNode, BLACK);
					setColor(parentOf(parentOf(p)), RED);
					//继续调整爷节点
					p=parentOf(parentOf(p));
				}
				else {
					//如果p是父节点的左孩子，还需要先右旋一下，使p的父节点下去。
					if(p==leftOf(parentOf(p))) {
						p=parentOf(p);
						rightRotate(p);
					}
					//父节点变黑，爷节点变红，以爷节点左旋转
					setColor(parentOf(p), BLACK);
					setColor(parentOf(parentOf(p)), RED);
					leftRotate(parentOf(parentOf(p)));
				}
			}
		}

		setColor(root, BLACK);
	}

	public void delete(int _data) {
		TNode t = root;
		if (_data == root.data && root.left == null && root.right == null){
			root = null;
			return;
		}
		int tmp = root.data;
		while (_data != tmp) {
			if(_data < tmp){
				t = t.left;
			} else {
				t = t.right;
			}
			tmp = t.data;
		}
		//找到要删除的节点t
		//节点t的三种情况
		//情况1：删除的是叶子节点
		if(t.left == null && t.right == null){
			deleteleaf(t);
		}

		//情况2：删除的节点只有一个子树
		TNode s = null;
		deleteonewaytree(t,s);

		//情况3：删除的节点有两个子树
		//交换节点与其中序后继节点的值，删除后继节点
		if(t.left != null && t.right != null) {
			s = t.right;
			while (s.left != null){
				s = s.left;
			}
			tmp = s.data;
			s.data = t.data;
			t.data = tmp;
			//转换为删除s：s对应上述情况1和情况2
			t = s;
			s = null;
			//情况1：删除的是叶子节点
			if(t.left == null && t.right == null) {
				deleteleaf(t);
			}
			deleteonewaytree(t,s);
		}
	}

	private void deleteleaf(TNode t){
		//情况1.1：t是红色的：无需修复
		//情况1.2：t是黑色的：需要修复
		if(t.color == BLACK){
			fixAfterDelete(t);
		}
		deleteNode(t);
	}

	private void deleteonewaytree(TNode t,TNode s){
		//情况2：删除的节点只有一个子树
		//删除节点，用其子节点代替它
		//被删节点为红色 直接提上来即可
		if(t.left == null && t.right != null){
			s = t.right;
		} else if (t.left != null && t.right == null){
			s = t.left;
		}
		if (s != null) {
			if(t.parent != null) {
				if (t == leftOf(t.parent)) {
					t.parent.left = s;
					s.parent = t.parent;
				} else {
					t.parent.right = s;
					s.parent = t.parent;
				}
			} else {
				this.root = s;
				s.parent = null;
			}
			//被删除节点是黑色 只需要把提上来的节点染黑即可
			if(t.color == BLACK) {
				s.color = BLACK;
			}
		}
	}

	/**
	 * 删除后的修复仅仅存在于被删除节点是黑色叶子节点时
	 */
	private void fixAfterDelete(TNode d) {
		while(d!=root && d.color == BLACK){
			//被删除节点是左节点
			if(d == leftOf(parentOf(d))) {
				//右兄弟
				TNode rnode = rightOf(parentOf(d));

				//右兄弟为红色？
				//兄弟和父亲交换颜色即可
				if(colorOf(rnode)==RED){
					setColor(rnode,BLACK);
					setColor(parentOf(d),RED);
					leftRotate(parentOf(d));
					rnode = rightOf(parentOf(d));
				}
				//近侄为黑 远侄为黑
				if(colorOf(leftOf(rnode)) == BLACK && colorOf(rightOf(rnode)) == BLACK){
					//兄弟节点变红 d指向父亲节点 调整
					setColor(rnode,RED);
					d=parentOf(d);
				} else{
					//近侄为红 远侄为黑  ->  近侄变黑 远侄变红
					if(colorOf(rightOf(rnode))==BLACK){
						setColor(leftOf(rnode),BLACK);
						setColor(rnode,RED);
						rightRotate(rnode);
						rnode=rightOf(parentOf(d));
					}
					//近侄子为黑 远侄为红
					setColor(rnode,colorOf(parentOf(d)));
					setColor(parentOf(d),BLACK);
					setColor(rightOf(rnode),BLACK);
					leftRotate(parentOf(d));
					d = root;
				}
			}


			else{
				TNode rnode = leftOf(parentOf(d));
				if(colorOf(rnode)==RED){
					setColor(rnode,BLACK);
					setColor(parentOf(d),RED);
					rightRotate(parentOf(d));
					rnode=leftOf(parentOf(d));
				}
				if(colorOf(rightOf(rnode))==BLACK&&colorOf(leftOf(rnode))==BLACK){
					setColor(rnode,RED);
					d=parentOf(d);
				}
				else{
					if(colorOf(leftOf(rnode))==BLACK){
						setColor(rightOf(rnode),BLACK);
						setColor(rnode,RED);
						leftRotate(rnode);
						rnode=leftOf(parentOf(d));
					}
					setColor(rnode,colorOf(parentOf(d)));
					setColor(parentOf(d),BLACK);
					setColor(leftOf(rnode),BLACK);
					rightRotate(parentOf(d));
					d=root;
				}
			}
		}
		setColor(d,BLACK);


//		while (d != root ){
//			System.out.println(d.data + "  "+root.left.color);
//			//判断兄弟节点颜色
//			//如果是左孩子节点                            case1
//			if(d.parent.left == d) {
//				//兄弟节点是红色
//				if(d.parent.right != null)
//					if(d.parent.right.color == RED) {
//						//交换颜色
//						System.out.println("case1");
//						d.parent.color = RED;
//						d.parent.right.color = BLACK;
//						leftRotate(d.parent);
//						continue;
//					}
//			}
//			//如果是右孩子节点
//			if(d.parent.right == d) {
//				//兄弟节点是红色
//				if(d.parent.left != null)
//					if(d.parent.left.color == RED) {
//						//交换颜色
//						System.out.println("case1");
//						d.parent.color = RED;
//						d.parent.left.color = BLACK;
//						rightRotate(d.parent);
//						continue;
//					}
//			}
//
//			//判断远侄节点颜色
//			//如果是左孩子节点                            case2
//			if(d.parent.left == d) {
//				if(d.parent.right != null)
//					if(d.parent.right.right != null) {
//						//远侄节点为红色
//						if (d.parent.right.right.color == RED) {
//							//父节点 兄弟节点颜色互换
//							System.out.println("case2");
//							Boolean tmp = d.parent.color;
//							d.parent.color = d.parent.right.color;
//							d.parent.right.color = tmp;
//							//染黑远侄节点
//							d.parent.right.right.color = BLACK;
//							leftRotate(d.parent);
//							return;
//						}
//					}else { //远侄节点为黑色        case3
//							//近侄子节点存在为红色
//							if(d.parent.right.left != null){
//								if(d.parent.right.left.color == RED) {
//									//兄弟节点和近侄节点互换颜色
//									System.out.println("case3");
//									Boolean tmp = d.parent.right.color;
//									d.parent.right.color = d.parent.right.left.color;
//									d.parent.right.left.color = tmp;
//									rightRotate(d.parent.right);
//									continue;
//								}
//							}
//						}
//				if(d.parent.right != null)
//					if(allBlack(d.parent.right)) { //case4
//						//父节点为红色
//						System.out.println("case45");
//						if(d.parent.color == RED){
//							d.parent.color = BLACK;
//							d.parent.right.color = RED;
//							return;
//						} else { //父亲节点为黑色 case5
//							d.parent.right.color = RED;
//							d = d.parent;
//							continue;
//						}
//					}
//			}
//			//如果是右孩子节点                            case2
//			if(d.parent.right == d) {
//				if(d.parent.left != null)
//					if(d.parent.left.left != null){
//						//远侄节点为红色
//						if(d.parent.left.left.color == RED) {
//							//父节点 兄弟节点颜色互换
//							System.out.println("case2");
//							Boolean tmp = d.parent.color;
//							d.parent.color = d.parent.left.color;
//							d.parent.left.color = tmp;
//							//染黑远侄节点
//							d.parent.left.left.color = BLACK;
//							rightRotate(d.parent);
//							return;
//						}
//					} else {//远侄节点为黑色         case3
//							//近侄子节点存在为红色
//							if(d.parent.left.right != null) {
//								if(d.parent.left.right.color == RED) {
//									//兄弟节点和近侄节点互换颜色
//									System.out.println("case3");
//									Boolean tmp = d.parent.left.color;
//									d.parent.left.color = d.parent.left.right.color;
//									d.parent.left.right.color = tmp;
//									leftRotate(d.parent.left);
//									continue;
//								}
//							}
//						}
//				if(d.parent.left != null)
//					if(allBlack(d.parent.left)) { //case4
//						//父节点为红色
//						System.out.println("case45");
//						if(d.parent.color == RED){
//							d.parent.color = BLACK;
//							d.parent.left.color = RED;
//							return;
//						} else { //父亲节点为黑色  case5
//							d.parent.left.color = RED;
//							d = d.parent;;
//							continue;
//						}
//					}
//			}
//		}
	}


	private void deleteNode(TNode d) {
		//删除d
		if(d.parent.left != null)
			if(d == d.parent.left){
				d.parent.left =null;
			}
		if(d.parent.right != null)
			if(d == d.parent.right){
				d.parent.right = null;
			}
	}
	/**
	 * 红黑树节点
	 *
	 *
	 */
	static class TNode{
		private TNode parent;
		private TNode left;
		private TNode right;
		private boolean color;
		private int data;

		public TNode(TNode parent, TNode left, TNode right, boolean color, int data) {
			this.parent = parent;
			this.left = left;
			this.right = right;
			this.color = color;
			this.data = data;
		}

		public TNode() {
		}

		public TNode(int data) {
			this.color=BLACK;
			this.data = data;
		}
		public TNode(TNode parent,int data) {
			this.parent = parent;
			this.color=BLACK;
			this.data = data;
		}

		public TNode getParent() {
			return parent;
		}

		public void setParent(TNode parent) {
			this.parent = parent;
		}

		public TNode getLeft() {
			return left;
		}

		public void setLeft(TNode left) {
			this.left = left;
		}

		public TNode getRight() {
			return right;
		}

		public void setRight(TNode right) {
			this.right = right;
		}

		public boolean isColor() {
			return color;
		}

		public void setColor(boolean color) {
			this.color = color;
		}

		public int getData() {
			return data;
		}

		public void setData(int data) {
			this.data = data;
		}




	}
}
