#!groovy


node('agent1') {

  echo "${env.BRANCH_NAME}"
  stage('Checkout SCM') {
    git branch: "${env.BRANCH_NAME}", credentialsId: 'jenkins_github', url: "git@github.com:shpatserman/webservice.git"
  }

}
