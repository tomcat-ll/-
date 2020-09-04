pipeline {
    agent none
    triggers {
        pollSCM 'H/15 * * * *'
    }
    stages {
        stage("build") {
            agent any
            steps {
                dir("dists") {
                    git branch: 'master', url: 'git@e.coding.net:cqyunji/jenkins-vita.git', poll: false
                }
                script {
                    // 子项目集合
                    def projs = ["."]
                    // 需要部署的环境集合
                    def envs = ["test"]
                    // 一级服务名称
                    def pipeline = load "$WORKSPACE/dists/jenkins/pipeline.groovy"
                    pipeline.build(projs, envs)
                }
            }
        }
    }
}
