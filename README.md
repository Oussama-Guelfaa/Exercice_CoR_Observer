```markdown
# TP Design Patterns: Chain of Responsibility, Observer, and Proxy

## Table of Contents
- [Introduction](#introduction)
- [Project Structure](#project-structure)
- [Design Patterns Implemented](#design-patterns-implemented)
  - [Chain of Responsibility](#chain-of-responsibility)
  - [Observer](#observer)
  - [Proxy (Bonus)](#proxy-bonus)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Examples](#examples)
- [Conclusion](#conclusion)
- [Author](#author)

## Introduction

Ce projet vise à implémenter deux patrons de conception (\emph{Design Patterns}) principaux dans un mini-serveur web en Java, avec une option supplémentaire pour utiliser un troisième patron en bonus. Les patrons mis en œuvre sont :

1. Chain of Responsibility (Chaîne de Responsabilité) : Pour gérer séquentiellement les requêtes via une série de gestionnaires (handlers).
2. Observer (Observable/Observer) : Pour loguer toutes les requêtes entrantes dans un fichier.
3. Proxy (Bonus) : Une alternative au patron Observer pour la journalisation des requêtes.

## Project Structure

Le projet est organisé de la manière suivante :



- FileLogger.java : Classe responsable du logging des requêtes.
- WebServer.java : Implémentation principale du serveur web.
- WebServerInterface.java : Interface définissant les méthodes du serveur web.
- WebServerProxy.java : Proxy pour intercepter et loguer les requêtes (bonus).
- Main.java : Point d'entrée du programme, configurant les handlers et les proxies.
- RequestHandler.java : Classe abstraite pour les handlers de la chaîne de responsabilité.
- ExistingContentCheckRequestHandler.java : Vérifie l'existence de la page demandée.
- PolicyCheckRequestHandler.java : Vérifie les droits de l'utilisateur.
- RenderContentHandler.java : Rend le contenu de la page si tout est conforme.
- WebRequest.java : Représentation d'une requête web.
- User.java : Représentation d'un utilisateur.

## Design Patterns Implemented

### Chain of Responsibility

Le patron Chain of Responsibility permet de traiter une requête en la passant à travers une chaîne de gestionnaires (handlers). Chaque handler décide soit de traiter la requête, soit de la passer au suivant dans la chaîne.

Classes impliquées :
- `RequestHandler.java` (Abstraite)
- `ExistingContentCheckRequestHandler.java`
- `PolicyCheckRequestHandler.java`
- `RenderContentHandler.java`

### Observer

Le patron Observer permet à un objet (Observable) de notifier automatiquement une liste d'observateurs lorsqu'un événement se produit. Dans ce projet, le serveur web notifie les observateurs chaque fois qu'une requête est reçue, permettant ainsi le logging des requêtes.

Classes impliquées :
- `WebRequestObservable.java` (Interface)
- `WebRequestObserver.java` (Interface)
- `WebServer.java`
- `FileLogger.java`

### Proxy (Bonus)

Le patron Proxy sert d'intermédiaire entre le client et l'objet réel, permettant d'ajouter des comportements supplémentaires comme le logging sans modifier la classe originale.

Classes impliquées :
- `WebServerInterface.java` (Interface)
- `WebServerProxy.java`
- `FileLogger.java`
- `WebServer.java`

## Prerequisites

- Java Development Kit (JDK) : Version 8 ou supérieure.
- Maven (optionnel) : Pour gérer les dépendances et la compilation.
- IDE Java (optionnel) : Comme IntelliJ IDEA, Eclipse ou VS Code.

## Installation

1. Cloner le dépôt :
   ```bash
   git clone https://github.com/Oussama-Guelfaa/Exercice_CoR_Observer
   cd tp-design-patterns
   ```

2. Compiler le projet :
   - Avec Maven :
     ```bash
     mvn compile
     ```
   - Sans Maven :
     ```bash
     javac src/*.java -d bin
     ```

## Usage

1. Exécuter le programme :
   - Avec Maven :
     ```bash
     mvn exec:java -Dexec.mainClass="Main"
     ```
   - Sans Maven :
     ```bash
     java -cp bin Main
     ```

2. Observer les logs :
   - Les requêtes sont loguées dans le fichier `logs.txt` généré à la racine du projet.

## Examples

Exécution sans Proxy (Observer Pattern) :
```bash
java -cp bin Main
```
Sortie Console :
```
Status 403 : user is not authorized to access this content
Status 404 : Page missing
Status 200 : Dashboard content here
Status 200 : Home content here
```
Contenu de `logs.txt` :
```
Request made to /dashboard by non admin user
Request made to /dashboard/nonExistingPage by admin user
Request made to /dashboard by admin user
Request made to /home by non admin user
```

Exécution avec Proxy (Bonus) :
- Modifier `Main.java` pour utiliser `WebServerProxy` au lieu de l'Observer.
- Recompiler et exécuter le programme.
  
Sortie Console :
```
Status 403 : user is not authorized to access this content
Status 404 : Page missing
Status 200 : Dashboard content here
Status 200 : Home content here
```
Contenu de `logs.txt` :
```
Request made to /dashboard by non admin user
Request made to /dashboard/nonExistingPage by admin user
Request made to /dashboard by admin user
Request made to /home by non admin user
```

## Conclusion

Ce projet illustre l'implémentation de deux patrons de conception fondamentaux en génie logiciel, ainsi qu'une alternative bonus. L'utilisation du Chain of Responsibility permet une gestion claire et séquentielle des requêtes, tandis que le Observer Pattern facilite le logging des requêtes de manière décentralisée. Le Proxy Pattern offre une alternative élégante pour intercepter et loguer les requêtes sans modifier la classe originale, renforçant ainsi la séparation des responsabilités et la flexibilité de l'architecture.

## Author

GUELFAA Oussama

- Encadré par : RICHAUD Quentin
- École : École Nationale Supérieure des Mines de Saint-Etienne, Institut Mines-Télécom
- Année : 2024-2025
```
