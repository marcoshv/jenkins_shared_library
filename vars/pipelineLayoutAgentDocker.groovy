def call(Map pipelineParams) {
    pipeline {
        agent {
            docker {
                image pipelineParams.agent.image
                args pipelineParams.agent.args
                label pipelineParams.agent.label
            }

            stages {
                stageClone.cloneRepo(pipelineParams.repoName, pipelineParams.branch)
                stageBuild()
                stagePackage()
                stage ('Stage publish') {
                    stagePublish.pubProcess1()
                    stagePublish.pubProcess2()
                }
            }
        }
    }
    
}