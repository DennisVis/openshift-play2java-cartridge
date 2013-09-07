# OpenShift Play 2 Java Cartridge

This cartridge will allow you to get up and running using Play Framework 2.1 on OpenShift.

## New application

Creat a new OpenShift application by using the following command:

``` 
rhc create-app {name} "http://cartreflect-claytondev.rhcloud.com/reflect?github=DennisVis/openshift-play2java-cartridge"
``` 

This will create a remote and local copy of a new Play Framework 2 Java application called {name} in a folder with the same name. Use this command like you would use 'play new'.
After the application is created and you have made changes to the code, push them with git and the cartridge will deploy the changes.


## Existing application

If you want to deploy an already existing project to OpenShift, create a new OpenShift app using the rhc command described above. 
You will not be using the locally created folder it is safe to delete this.
Now add the git repository of the new OpenShift application as a remote repository to your existing project like so:

Then follow the steps described here https://www.openshift.com/kb/kb-e1006-sync-new-git-repo-with-your-own-existing-git-repo starting from "Merge remote repo with local repo:".


## Console

You can also use the console to create a new OpneShift application. Choose to create a new application and just enter the url "http://cartreflect-claytondev.rhcloud.com/reflect?github=DennisVis/openshift-play2java-cartridge" in the input that says "URL to cartridge definition" below the header "Code Anything" and click next.


## First build

If you create a new application, you will not be able to visit it in your browser yet. If you do, you'll recieve a 503 error. This is due to the fact that I haven't found a way to build the application when the cartridge is first created. The build process (clean compile stage) will take too long and in stead of creating a new application you'll recieve a time-out.
Therefore, the first build will occur after your first push to the OpenShift repo. This may take some time and the first time you push changes will probably take a few minutes. Luckily you can track the process in the console after you have issued the push command.


## Help more then welcome!!

I have created this cartridge because I was fed up with the lack of Play support on OpenShift. I am by no means, however, an experienced batch hacker and would love any kind of help. Whether it be tips, suggestions, remarks or complete pull requests they are all welcome! I would only like to suggest that you create a ticket before creating a pull request.


## Thanks!

I would like to thank opensas for his work on the play2-openshift-quickstart (https://github.com/opensas/play2-openshift-quickstart)! A lot of scripts I use in this cartridge are heavily based on his. 
