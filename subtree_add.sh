#!/bin/bash


# ------------------------------------------------------------------
# Script Name: subtree_add.sh
# Description: This script automates the process of adding a Git
#              subtree to your repository.
# Instructions: To use this script, provide the URL of the Git
#               repository that you want to add as a subtree.
#               The name of the subtree will be derived from the URL.
# Example:     ./subtree_add.sh https://github.com/progress0407/yhkim-mvc2-message.git
# ------------------------------------------------------------------


# Colors
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[0;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color


echo_blue() {
    echo -e "${BLUE}$1${NC}"
}


echo_red() {
    echo -e "${RED}$1${NC}"
}


# Check for correct number of arguments
if [ "$#" -ne 1 ]; then
    echo_red "Usage: $0 {repository url}"
    exit 1
fi


# Extract repository name from URL
extract_repo_name() {
    local url=$1
    local repo_name_with_extension=$(basename "$url")
    echo ${repo_name_with_extension%.git}
}


# Assign arguments to variables for better readability
repo_url=$1
repo_name=$(extract_repo_name "$repo_url")
target_dir=$repo_name
branch_name="main"


# Add the remote
git remote add $repo_name $repo_url
echo_blue "\nadd remote git repo"


# Add the subtree
git subtree add --prefix=$target_dir $repo_name $branch_name
echo_blue "add subtree"


# Add and commit changes
git add -A
git commit -m "\nadd $repo_name"
echo_blue "commit"


# Remove the remote
git remote remove $repo_name
echo_blue "\nremove git repo for subtree"


# Output Git Log to Verify it worked correctly
echo_blue "\nPrinting git log for verification..."
git log --oneline -n 6


# Output Pulled Directory to Verify
echo_blue "\nPrinting Pulled Directory to verification"
ls -half | grep $target_dir

