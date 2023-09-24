Pour afficher le plateau de jeu:
1. Pour afficher les couleurs, il est conseillé de changer la version de maven 3.3.9.
2. Pour afficher les pions en unicode, il faut ajouter  **-J-Dfile.encoding=UTF-8** à la propriété **netbeans_default_options** dans le fichier **etc/netbeans.conf**.
Ensuite, redémarrez NetBeans.
3. Dans les propriétés du projet, il faut vérifier si dans la parties _Sources_ , l'_Encoding_ est en _UTF-8_.
Lien de StackOverFlow pour l'unicode :https://stackoverflow.com/questions/53257763/netbeans-9-print-unicode-characters

J'ai créé une deuxième méthode de _DisplayBoard()_ (dans TextView) qui permet d'afficher les déplacements possibles sur le plateau. Les cases où le joueur peut se déplacer se mettent en rouge.