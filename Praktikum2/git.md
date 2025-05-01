# Git Status erklären

Der Output von `git status` zeigt alle änderungen an dateien, seit dem letzten commit. 
Unter `Changes not staged for commit` sind änderungen an dateien die bei dem letzen Commit schon existierten.
Die `Untracked files` sind seit dem neu entstandene dateien.

Um nur die Änderungen an foo.java zu commiten eignen sich die Folgenden Befehler
git add foo.java
git stage foo.java
git commit -m "Created foo.java"

# Git Spiel

## 1.

// Was passierte an tag 01?
git checkout e779
git diff HEAD~5

// Wann hat der Held zum ersten Mal 4 experience Punkte?
git log -p --reverse -G'experience.*4' stats.md 
-> tag 1.3

// Wann hat der Held zum ersten Mal 10 hunger Punkte?
git log -p --reverse -G'hunger.*10' stats.md
-> tag 2

//Wie viele Heiltränke hat der Held insgesamt in seinem Rucksack gehabt?
git log -p --reverse rucksack.md
tag 3.14
tag 4.4
-> 2 tränke

// Was hat der Held im Shop gekauft? Und wie viel Gold hat er dafür bezahlt?
git log -p --reverse shopkeeper.md
-> 2 Zwergenbrote für 10 Gold?

// Was passierte zwischen tag 03 und tag 04, d.h. was änderte sich zwischen diesen Commits?
git checkout 9434 # (Tag 4)
git diff HEAD~17# (Tag 3)

->  10 Gold ausgegeben
    + 5 hp
    - 10 hunger

// Hat der Held etwas gegessen? Falls ja, was und wann?
der Held hat am Tag 3.17 sich ein 2 Zwergen Brote gekauf und gegessen

## 2.

nv stats.md
git commit --amend --no-edit
git push --force

## 3.

nv questlog.md
git commit -a -m "tag 04.6"
git push

## 4.

nv .
git commit -a -m "tag 04.7"
git push

## 5.
nv .
git add .
git commit -a -m "tag 04.8"
git push



