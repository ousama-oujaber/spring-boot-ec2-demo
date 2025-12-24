# Application Spring Boot pour AWS EC2 🚀

## Description du projet

Cette application est un backend simple développé avec **Spring Boot** et **Java 17**. Elle expose une API REST minimaliste conçue pour démontrer comment déployer rapidement une application backend sur **AWS EC2**.

L'application fournit un seul endpoint :
- `GET /api/hello` : Retourne un message de bienvenue au format JSON

Aucune base de données n'est utilisée, ce qui rend le projet parfait pour une démonstration rapide et légère.

---

## Prérequis

Avant de commencer, assurez-vous d'avoir les éléments suivants installés :

### Sur votre machine locale :
- **Java 17** ou supérieur
- **Maven 3.6+**
- **Git**

### Pour le déploiement sur AWS :
- Un compte AWS
- Une instance EC2 (Amazon Linux 2 ou Amazon Linux 2023 recommandé)
- Les ports **22** (SSH) et **8080** (API) ouverts dans le Security Group

---

## 🏃 Lancer le projet localement

### 1. Cloner le projet

```bash
git clone https://github.com/votre-username/ec2-spring-boot-demo.git
cd ec2-spring-boot-demo
```

### 2. Compiler le projet avec Maven

```bash
./mvnw clean package
```

Ou si Maven est installé globalement :

```bash
mvn clean package
```

### 3. Lancer l'application

```bash
java -jar target/ec2-0.0.1-SNAPSHOT.jar
```

### 4. Tester l'API

Ouvrez un navigateur ou utilisez `curl` :

```bash
curl http://localhost:8080/api/hello
```

**Réponse attendue :**

```json
{
  "message": "Hello from EC2 🚀"
}
```

---

## ☁️ Déployer sur AWS EC2 (étape par étape)

### Étape 1 : Créer une instance EC2

1. Connectez-vous à la **Console AWS**
2. Allez dans **EC2** → **Launch Instance**
3. Choisissez une image : **Amazon Linux 2023** (ou Amazon Linux 2)
4. Type d'instance : **t2.micro** (éligible à l'offre gratuite)
5. Créez ou sélectionnez une paire de clés SSH (fichier `.pem`)
6. Configurez le **Security Group** :
   - Port **22** (SSH) : ouvert depuis votre IP
   - Port **8080** (HTTP custom) : ouvert depuis `0.0.0.0/0` (ou votre IP pour plus de sécurité)
7. Lancez l'instance

### Étape 2 : Se connecter à l'instance EC2

```bash
ssh -i /chemin/vers/votre-cle.pem ec2-user@<ADRESSE-IP-PUBLIQUE>
```

Remplacez `<ADRESSE-IP-PUBLIQUE>` par l'adresse IP publique de votre instance EC2.

### Étape 3 : Installer Java 17 sur EC2

Une fois connecté à votre instance, installez Java :

```bash
sudo yum update -y
sudo yum install java-17-amazon-corretto-devel -y
```

Vérifiez l'installation :

```bash
java -version
```

Vous devriez voir Java 17 installé.

### Étape 4 : Installer Git

```bash
sudo yum install git -y
```

### Étape 5 : Cloner le projet depuis GitHub

```bash
git clone https://github.com/votre-username/ec2-spring-boot-demo.git
cd ec2-spring-boot-demo
```

> ⚠️ Remplacez `votre-username` par votre nom d'utilisateur GitHub.

### Étape 6 : Compiler le projet avec Maven

Le projet inclut le wrapper Maven, donc pas besoin d'installer Maven séparément :

```bash
chmod +x mvnw
./mvnw clean package
```

Cette commande va télécharger les dépendances et créer un fichier JAR exécutable dans le dossier `target/`.

### Étape 7 : Lancer l'application

```bash
java -jar target/ec2-0.0.1-SNAPSHOT.jar
```

L'application démarre sur le port **8080**.

### Étape 8 : Accéder à l'API depuis Internet

Depuis votre machine locale (ou n'importe où), testez l'API en utilisant l'IP publique de votre instance EC2 :

```bash
curl http://<ADRESSE-IP-PUBLIQUE>:8080/api/hello
```

**Réponse attendue :**

```json
{
  "message": "Hello from EC2 🚀"
}
```

Vous pouvez également ouvrir cette URL dans un navigateur :

```
http://<ADRESSE-IP-PUBLIQUE>:8080/api/hello
```

---

## 🔧 Lancer l'application en arrière-plan (optionnel)

Si vous voulez que l'application continue de fonctionner même après avoir fermé la session SSH, utilisez `nohup` :

```bash
nohup java -jar target/ec2-0.0.1-SNAPSHOT.jar > app.log 2>&1 &
```

Pour arrêter l'application plus tard :

```bash
ps aux | grep java
kill <PID>
```

---

## 📡 Exemple de commande curl pour tester

### Test local (sur votre machine) :

```bash
curl http://localhost:8080/api/hello
```

### Test sur EC2 (depuis n'importe où) :

```bash
curl http://<IP-PUBLIQUE-EC2>:8080/api/hello
```

### Test avec formatage JSON :

```bash
curl http://<IP-PUBLIQUE-EC2>:8080/api/hello | json_pp
```

---

## 📚 Conclusion : Pourquoi utiliser AWS EC2 pour le déploiement backend ?

**AWS EC2 (Elastic Compute Cloud)** est un service cloud qui permet de louer des serveurs virtuels pour exécuter des applications. Voici pourquoi c'est une excellente solution pour déployer un backend Spring Boot :

### ✅ Avantages d'EC2 :

1. **Contrôle total** : Vous avez accès à une machine Linux complète. Vous pouvez installer ce que vous voulez (Java, Python, Node.js, etc.).

2. **Flexible** : Vous pouvez choisir la taille de l'instance en fonction de vos besoins (t2.micro pour des tests, m5.large pour de la production).

3. **Scalable** : Vous pouvez facilement augmenter ou diminuer les ressources (CPU, RAM) selon la charge.

4. **Pay-as-you-go** : Vous ne payez que pour ce que vous utilisez. Il existe même une offre gratuite (Free Tier) pour les nouveaux comptes AWS.

5. **Pas de configuration locale** : Une fois déployé sur EC2, votre backend est accessible 24/7 depuis Internet, sans avoir à laisser votre ordinateur allumé.

6. **Intégration AWS** : Vous pouvez facilement connecter votre backend à d'autres services AWS (RDS pour une base de données, S3 pour du stockage, etc.).

### 🎯 Cas d'usage typiques :

- **Déploiement rapide** d'une API REST pour un projet ou une démo
- **Tests en conditions réelles** (accès public, latence réseau, etc.)
- **Hébergement de backends** pour des applications mobiles ou frontend
- **Environnement de développement** ou de staging dans le cloud

---

## 📝 Auteur

Projet créé pour une démonstration pédagogique sur le déploiement d'applications Spring Boot sur AWS EC2.

---

## 📄 Licence

Ce projet est open source et disponible sous la licence MIT.

