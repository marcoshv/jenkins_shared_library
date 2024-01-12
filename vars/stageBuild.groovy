def call(int a, int b ) {
    proc1(a,b)
    proc2()
}
    
def proc1(int a, int b ) {
    echo "proc1"
    echo "$a+$b"
}

def proc2() {
    echo "proc2"
}
