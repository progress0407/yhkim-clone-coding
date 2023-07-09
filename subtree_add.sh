#!/bin/bash

# Check for correct number of arguments
if [ "$#" -ne 2 ]; then
    echo "Usage: $0 {repository name} {repository url}"
    exit 1
fi

# Assign arguments to variables for better readability
repo_name=$1
repo_url=$2
target_dir=$repo_name
branch_name="main"

# Add the remote
git remote add $repo_name $repo_url
echo "add remote git repo"

# Add the subtree
git subtree add --prefix=$target_dir $repo_name $branch_name
echo "add subtree"

# Add and commit changes
git add -A
git commit -m "add $repo_name"
echo "commit"

# Remove the remote
git remote remove $repo_name
echo "remove git repo for subtree"
