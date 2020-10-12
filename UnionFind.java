//based on https://www.cs.waikato.ac.nz/~bernhard/317/source/graph/UnionFind.java

public class UnionFind {
    private int[] _parent;
    //    private int[] _label;
    private int max;


    public int find(int i) {

        int p = _parent[i];
        if (i == p) {
            return i;
        }
        return _parent[i] = find(p);

    }


    public int union(int i, int j) {

        int root1 = find(i);
        int root2 = find(j);

        if (root2 == root1) return root1;

        if (root1 > root2) {
            //_label[root1] > _label[root2]) {
            _parent[root1] = root2;
            return root2;
        } else //(_label[root2] > _label[root1])
        {
            _parent[root2] = root1;
            return root1;
        }
        //else {
        //   _parent[root2] = root1;
        //   _label[root1]++;
        //}
    }


    public UnionFind(int max) {

        _parent = new int[max];
        this.max = max;
//        _label = new int[max];

        for (int i = 0; i < max; i++) {
            _parent[i] = i;
        }
    }

    // Version of flatten for returning consecutive labels
/*    public void flatten() {
        int k = 0;
        for (int i = 0; i < max ; i++) {
            if ( _parent[i] != i )
                _parent[i] = _parent[_parent[i]];
            else
                _parent[i] = k++;
        }
        */
    public void flatten1() {
        for (int i = 0; i < max; i++) {
            _parent[i] = _parent[_parent[i]];
        }
    }

    public void flatten2() {
        int k = 1;
        for (int i = 0; i < max; i++) {
            if (_parent[i] != i)
                _parent[i] = _parent[_parent[i]];
            else
                _parent[i] = k++;
        }
    }

      public String toString() {
      return "<UnionFind\np " + java.util.Arrays.toString(_parent) + "\n>";
  }
}