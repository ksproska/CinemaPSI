# CinemaPSI
## Overall architecture
![architecture.drawio.png](.images/architecture.drawio.png)

---

## Technologie wspierające rozwijanie oprogramowania - treść zadania
W trakcie tego zadania (miniprojektu) zadaniem zespołu jest stworzenie środowiska umożliwiającego ciągłą budowę i wdrażanie przy użyciu aplikacji Jenkins.

### Środowisko budujące
Uruchomić na osobnych instancjach EC2 następujące usługi:
1. Jenkins. Instalacja opisana [tutaj](https://www.jenkins.io/doc/book/installing/).
2. Węzeł budujący (z Dockerem).
3. Serwer repozytoriów Git. Zachęcam do użycia aplikacji [Gogs](https://gogs.io/docs/installation) dostępnej również w postaci kontenera. Alternatywnie można użyć GitHuba lub AWS Code Commit.
4. Lokalny rejestr obrazów Docker. Można wykorzystać gotowy obraz dostępny w witrynie
   [DockerHub](https://docs.docker.com/registry/). Alternatywnie można użyć witryny DockerHub lub AWS Elastic Container Registry.
   Skonfigurować środowisko w taki sposób, żeby kod wysłany do repozytorium Git poleceniem
   `git push` powodował automatyczne uruchomienie na serwerze Jenkins zadania budującego
   kod i wysyłającego gotowy kontener do rejestru.

### Środowisko uruchomieniowe
Wykorzystać możliwości oferowane przez chmurę AWS do uruchomienia aplikacji:
1. Wykorzystać bazy danych oferowane przez AWS (rozwiązanie preferowane).
2. Udostępnić część statyczną (np. zbudowany front-end, arkusze styli, obrazki) przez
   usługę S3.
3. Udostępnić część back-end w wybrany przez siebie sposób:
   - używając usługi AWS ElasticBeanstalk
   - używając usługi AWS Elastic Container Service
   - używając usługi AWS Elastic Kubernetes Service
   - używając własnej instancji EC2
