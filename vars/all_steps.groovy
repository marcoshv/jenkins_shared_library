def call(String dockerAgent, String repoUrl, String branch) {
    pipeline {
        agent {
        docker dockerAgent
        }
        stages {
            stage('Checkout') {
                steps {
                    checkout([$class: 'GitSCM', branches: [[name: '*/' + branch]],
                        userRemoteConfigs: [[url: repoUrl]]])
                }
            }
            stage('Code validation') {
                steps {
                    sh '''#!/bin/bash
                        echo "Running code validation"
                        ls -la
                        python -m venv venv
                        chmod -R a+rwx venv
                        source venv/bin/activate
                        python -m pip install --upgrade pip
                        pip install flake8
                        flake8 --exclude venv/ --ignore=W291 .
                        FLAKE8_EXIT_CODE=$?
                        echo "Flake8 exit code: $FLAKE8_EXIT_CODE"
                        if [ $FLAKE8_EXIT_CODE -ne 0 ]; then
                            echo "Flake8 analysis failed. Exiting."
                            exit 1
                        fi
                    '''
                }
            }
        }
    }
}