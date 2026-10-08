# MCA 2027 Mobile Application Development: Questions 1–14

**Fourteen separate Android Studio Java projects (source-only).** Each `Q01`...`Q14` folder contains an `app/src/main/` tree and its own README. The sample package name is `com.example.labapp` for all questions so that a single newly created Android Studio **Empty Views Activity** project can be used for practising one experiment at a time.

## How to run

1. Create **New Project -> Empty Views Activity** in Android Studio, choose **Java**, and set the package/namespace to `com.example.labapp`. Ensure the project's `minSdk` is at least 23.
2. From **one** `Qxx` folder, copy its `app/src/main/java`, `app/src/main/res/layout`, and `app/src/main/AndroidManifest.xml` into your project (merging or replacing files as appropriate).
3. Run the app on your emulator. Do not copy all 14 experiments into the same project at once: their `MainActivity` classes intentionally have the same name.
4. For an **Options Menu**, keep an Android theme with an Action Bar; the provided manifests use `@android:style/Theme.Material.Light`.
5. These examples deliberately avoid external dependencies.

## Contents

| Q | App | Activities |
|---|---|---|
| 1 | Quiz App | 3 |
| 2 | Student Profiles and Marks | 2 |
| 3 | Grocery Billing | 1 |
| 4 | Pizza Shop | 4 |
| 5 | Marks Card Generator | 1 |
| 6 | Attendance Tracking | 2 |
| 7 | Faculty Leave Management | 2 |
| 8 | E-Library | 1 |
| 9 | Anonymous Grievances | 1 |
| 10 | Placement Coordination | 2 |
| 11 | Smart Room Climate & Concierge | 2 |
| 12 | Fitness Tracking | 2 |
| 13 | Patient Appointment & Clinic Dashboard | 2 |
| 14 | Vehicle Rental System & Drive Dashboard | 2 |

**Security limitation:** These are short, offline **lab demonstrations**. Passwords are hardcoded in examples that require a login; SharedPreferences in `MODE_PRIVATE` are *not encrypted*. Do not use these samples with real patient, faculty, student, or account data, and do not describe them as production-secure. Actual authentication and tamper-resistant marks/attendance require a proper identity/backend solution.

**Persistence:** SharedPreferences values remain after activities close and after logging out. They can be cleared by uninstalling the app or clearing app data.

## Upload to GitHub manually

After extracting the ZIP, create a new empty GitHub repository. In a terminal from the extracted folder run:

```bash
git init
git add .
git commit -m "Add MCA 2027 Android lab programs Q01-Q14"
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPOSITORY.git
git push -u origin main
```

GitHub may ask you to authenticate with the GitHub CLI or a personal access token. Change `YOUR_USERNAME` and `YOUR_REPOSITORY` before pushing.
