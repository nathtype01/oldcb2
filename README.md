# oldcb2

**avant tout : désactiver le module "hypixel mods" dans les options sinon ça lag
de baisé dès que y'a des nametags affichés**

## c'est quoi

quelques classes décompilées et patchées du client offline cheatbreaker 1.8.9 
+ le vrai jar obfusqué original + les sources patchées des quelques classes qui avaient
des bugs

le client de base marchait plus sur plusieurs trucs vu que c'est un vieux build
de 2020 dont des endpoints externes sont morts depuis

## c'était cassé pourquoi

- parsing des arguments de lancement (jopt-simple) qui crash direct dès qu'on
  lui passe une vraie session (accesstoken uuid etc) peu importe la valeur
- écran de login mojang legacy (login/mdp) qui se réaffichait en boucle à
  chaque tick vu que l'authserver mojang existe plus et que "pas authentifié"
  était donc toujours vrai
- les capes optifine chargeaient jamais vu que l'url pointait vers le proxy
  cosmetics perso du build ("offline") mort depuis des années au lieu du vrai
  serveur optifine

tout ça est patché dans `src/` (chaque fichier a un commentaire en haut qui
explique le patch en détail)

## structure

- `original-oldcb/` le jar le json et les natives d'origine tels quels
- `src/` les sources décompilées puis patchées (juste les classes touchées
  pas tout le jar)
- `build.bat` recompile `src/` et réassemble un jar+json patché direct dans
  `original-oldcb/`

## comment build

lance `build.bat` depuis la racine du repo ça demande juste un jdk 8+ et 3 petites libs que le launcher officiel a déjà téléchargées
si t'as déjà joué à une version 1.8.x avant (sinon lance n'importe laquelle
une fois pour les récupérer avant de relancer le script)

## comment jouer

copie le jar patché et le json généré par `build.bat` dans
`%appdata%\.minecraft\versions\oldcb2\` puis
choisis cette version dans le launcher minecraft officiel et connecte toi
avec ton compte microsoft normalement
