// joas47

/**
 * Detta är den enda av de tre klasserna ni ska göra några ändringar i. (Om ni
 * inte vill lägga till fler testfall.) Det är också den enda av klasserna ni
 * ska lämna in. Glöm inte att namn och användarnamn ska stå i en kommentar
 * högst upp, och att en eventuell paketdeklarationen måste plockas bort vid inlämningen för
 * att koden ska gå igenom de automatiska testerna.
 * <p>
 * De ändringar som är tillåtna är begränsade av följande:
 * <ul>
 * <li>Ni får INTE byta namn på klassen.
 * <li>Ni får INTE lägga till några fler instansvariabler.
 * <li>Ni får INTE lägga till några statiska variabler.
 * <li>Ni får INTE använda några loopar någonstans. Detta gäller också alternativ
 * till loopar, så som strömmar.
 * <li>Ni FÅR lägga till fler metoder, dessa ska då vara privata.
 * <li>Ni får INTE låta NÅGON metod ta en parameter av typen
 * BinarySearchTreeNode. Enbart den generiska typen (T eller vad ni väljer att
 * kalla den), String, StringBuilder, StringBuffer, samt primitiva typer är
 * tillåtna.
 * </ul>
 *
 * @param <T>
 * @author joas47
 */

public class BinarySearchTreeNode<T extends Comparable<T>> {

    private T data;
    private BinarySearchTreeNode<T> left;
    private BinarySearchTreeNode<T> right;

    public BinarySearchTreeNode(T data) {
        this.data = data;
    }

    public boolean add(T data) {
        int compareResult = data.compareTo(this.data);

        if (compareResult < 0) {
            if (left == null) {
                left = new BinarySearchTreeNode<>(data);
                return true;
            } else {
                return left.add(data);
            }
        } else if (compareResult > 0) {
            if (right == null) {
                right = new BinarySearchTreeNode<>(data);
                return true;
            } else {
                return right.add(data);
            }
        } else {
            return false;
        }
    }

    public boolean contains(T data) {
        int compareResult = data.compareTo(this.data);

        if (compareResult == 0) {
            return true;
        } else if (compareResult < 0 && left != null) {
            return left.contains(data);
        } else if (compareResult > 0 && right != null) {
            return right.contains(data);
        } else {
            return false;
        }
    }

    private T findMin() {
        if (left != null) {
            return left.findMin();
        }
        return data;
    }

    public BinarySearchTreeNode<T> remove(T data) {
        if (contains(data)) {
            return removeRecursively(data);
        } else return this;
    }

    private BinarySearchTreeNode<T> removeRecursively(T data) {

        int compareResult = data.compareTo(this.data);

        if (compareResult < 0 && left != null) {
            left = left.removeRecursively(data);
        } else if (compareResult > 0 && right != null) {
            right = right.removeRecursively(data);
        } else {
            if (left == null) {
                return right;
            } else if (right == null) {
                return left;
            }
            this.data = right.findMin();
            right = right.removeRecursively(this.data);
        }
        return this;
    }

    public int size() {
        int leftCount = 0;
        int rightCount = 0;

        if (left != null) {
            leftCount += left.size();
        }
        if (right != null) {
            rightCount += right.size();
        }
        return leftCount + rightCount + 1;
    }

    public int depth() {
        if (left == null && right == null) {
            return 0;
        }
        int leftHeight = 0;
        int rightHeight = 0;
        if (left != null) {
            leftHeight = left.depth();
        }
        if (right != null) {
            rightHeight = right.depth();
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public String toString() {
        StringBuilder inOrder = new StringBuilder();
        inOrderTraversal(inOrder);
        if (size() >= 1) {
            inOrder = new StringBuilder(inOrder.substring(0, inOrder.length() - 2));
        }
        return inOrder.toString();
    }

    private void inOrderTraversal(StringBuilder inOrder) {
        if (left != null) {
            left.inOrderTraversal(inOrder);
        }
        inOrder.append(this.data).append(", ");
        if (right != null) {
            right.inOrderTraversal(inOrder);
        }
    }

    // Alternativ toString, OBS har med ett extra kommatecken.
    /*
    public String toString() {
       StringBuilder stringBuilder = new StringBuilder();
        if (left != null) {
            stringBuilder.append(left);
        }
        stringBuilder.append(data).append(", ");
        if (right != null) {
            stringBuilder.append(right);
        }
        return stringBuilder.toString();
    }*/
}