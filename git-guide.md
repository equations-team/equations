# git
## commit messages
It is important that our commit messages are clear, and follow the same conventions to reduce confusion and enhance clarity. Commit messages should be in the present tense and should concisely explain what the commit has done to change the repository. Example message:

` “Add updated migration scripts” `

Your pull requests (PRs) should not be composed of many commits that complete a single task. If you have many commits that all complete the same goal, you can squash or fixup the commit messages to reduce it to a manageable number of commits. To do this, you will need to do an interactive rebase, specify the number of commits in your log that you would like to edit, and then force push those changes up to your upstream branch.

` git rebase -i HEAD~<number_of_commits> `

The above command will open a file in vim, with which you can edit the commit history.

## branches

In order to make changes to the repo, you can not push directly to master. You will need to create your own branches, which can be submitted as pull requests to be reviewed and merged into master. To create a branch:

` git checkout branch <branch name> `

You should create branches with names that follow this convention:

` <first_initial><last_name>/<what-branch-is-for> `

*Example*:

` git checkout branch ohaney/update-read-me `

When working with a branch over multiple days, you may need to sync the states between your branch and the master branch so that you can stay up to date with all of the changes that people are making to the repo. When you want to do this and you’ve checked out the branch you want to sync, use:

` git rebase master `

To push the branch upstream so that you can submit a pull request:

` git push -u origin <name-of-branch> `


