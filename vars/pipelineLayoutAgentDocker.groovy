def call(Map pipelineParams) {
    pipeline {
        agent {
            docker {
                image pipelineParams.image
            }
        }

        stages {
            stage('Clone Repo') {
                steps {
                    script {
                        stageClone.cloneRepo(repo, branch)
                    }
                }
            }
            stageClone(pipelineParams.repoName, pipelineParams.branch)
            stage('Stage Build') {
                steps {
                    script {
                        stageBuild.proc1()
                        stageBuild.proc2()
                    }
                }
            }
            stagePackage()
            stage ('Stage publish') {
                steps {
                    script {
                        stagePublish.pubProcess1()
                        stagePublish.pubProcess2()
                    }
                }
            }
        }
    }
    
}