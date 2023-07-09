#!/bin/bash

# Check for correct number of arguments
if [ "$#" -ne 4 ]; then
    echo "Usage: $0 {repository name} {repository url} {target directory name} {branch name}"
    exit 1
fi

# Assign arguments to variables for better readability
repo_name=$1
repo_url=$2
target_dir=$3
branch_name=$4

# Add the remote
git remote add $repo_name $repo_url

# Add the subtree
git subtree add --prefix=$target_dir $repo_name $branch_name

# Add and commit changes
git add -A
git commit -m "add $repo_name"

# Remove the remote
git remote remove $repo_name
