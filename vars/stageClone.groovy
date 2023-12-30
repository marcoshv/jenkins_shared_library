def cloneRepo(String repo, String branch) {
    checkout([$class: 'GitSCM', branches: [[name: '*/' + branch]],
             userRemoteConfigs: [[url: repoUrl]]])
}

def call(String repo, String branch) {
    stage('Clone Repo') {
        cloneRepo(repo, branch)
    }
}