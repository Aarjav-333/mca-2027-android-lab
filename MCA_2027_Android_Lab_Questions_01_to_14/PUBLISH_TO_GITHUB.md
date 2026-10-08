# Publish these 14 lab exercises to GitHub

The ZIP contains **source files** for 14 separate lab exercises. Each `Qxx` folder can be copied into an Android Studio **Empty Views Activity** Java project. These are not standalone Gradle builds.

## Option A — GitHub CLI (fastest)

1. Install Git and [GitHub CLI](https://cli.github.com/) if needed.
2. Extract this ZIP, open a terminal in this folder, and run:

```bash
gh auth login
git init
git add .
git commit -m "Add MCA 2027 Android lab questions 1-14"
git branch -M main
gh repo create mca-2027-android-lab --public --source=. --remote=origin --push
```

GitHub CLI automatically creates the remote repo under your connected GitHub account and publishes the `main` branch. Change `--public` to `--private` if preferred. If you already created a repository, use Option B instead.

## Option B — Use an existing empty GitHub repository

Create an empty repository on [GitHub](https://github.com/new) with no README, `.gitignore`, or license, then run:

```bash
git init
git add .
git commit -m "Add MCA 2027 Android lab questions 1-14"
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPOSITORY.git
git push -u origin main
```

Replace `YOUR_USERNAME` and `YOUR_REPOSITORY` with your own values. GitHub requires authentication for the push.

## Open one question in Android Studio

1. Create an **Empty Views Activity** project in **Java** with package name `com.example.labapp`.
2. Copy files from only **one** Qxx folder into the matching paths of your Android Studio project, replacing/merging as necessary.
3. Run that experiment. The Qxx folders are independent and use the same Java class names intentionally.

**Note:** Any credentials, booking confirmations, student marks, or identity checks here are for offline lab demonstrations; these are not production-security implementations.
