# Git Status erklären

Der Output von `git status` zeigt alle änderungen an dateien, seit dem letzten commit. 
Unter `Changes not staged for commit` sind änderungen an dateien die bei dem letzen Commit schon existierten.
Die `Untracked files` sind seit dem neu entstandene dateien.

Um nur die Änderungen an foo.java zu commiten eignen sich die Folgenden Befehler
git add foo.java
git stage foo.java
git commit -m "Created foo.java"


