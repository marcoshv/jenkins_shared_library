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
            stage('Stage Build') {
                steps {
                    script {
                        stageBuild.proc1()
                        stageBuild.proc2()
                    }
                }
            }
            stage ('Stage Package') {
                stagePackage('Stage Package')
            }
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