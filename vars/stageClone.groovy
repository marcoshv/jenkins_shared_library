def call(String repo, String branch) {
    checkout([$class: 'GitSCM', branches: [[name: '*/' + branch]],
             userRemoteConfigs: [[url: repoUrl]]])
}