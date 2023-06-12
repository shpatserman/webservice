#!groovy


node('agent1') {

  echo "${env.BRANCH_NAME}"
  stage('Checkout SCM') {
    git branch: 'branchName', credentialsId: 'your_credentials', url: "giturlrepo"
  }

}
