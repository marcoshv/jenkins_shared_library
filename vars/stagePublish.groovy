def pubProcess1() {
    echo "pubProcess1"
}

def pubProcess2() {
    sh '''
        ls
        echo "pubProcess2"
    '''
}