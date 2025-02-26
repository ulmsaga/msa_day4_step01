# Jenkins Pipeline Structure

## Directory Structure
```
@jenkins/
├── dockerfiles/               
│   └── configserver.Dockerfile
│
├── k8s/         
│   └── configserver.template.yaml
│
├── pipelines/
│   └── configserver.jenkins/
│
configserver
│
eurekaserver
│
gatewayserver
│

```

## 구조 설명
- `pipelines/`: 각 마이크로서비스별 Jenkins 파이프라인 파일
- `k8s/`: 각 마이크로서비스별 k8s용 deploy, service용 tempfile
- `dockerfiles/`: 각 마이크로서비스별 Dockerfile

