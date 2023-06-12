#!groovy


node('agent1') {

  echo "${env.BRANCH_NAME}"
  stage('Checkout SCM') {
    git branch: "${env.BRANCH_NAME}", credentialsId: '3a58a7d8-1eeb-424a-9407-951e2c50ecb7', url: "https://github.com/shpatserman/webservice.git"
  }

}
