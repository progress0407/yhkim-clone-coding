#!/bin/bash

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

# Assign arguments to variables for better readability
repo_name="later init"
repo_url=$1
target_dir=$repo_name
branch_name="main"

# Assign repo_name
extract_repo_name() {
    local repo_name_with_extension=$(basename $repo_url)
    local repo_name=${repo_name_with_extension%.git}

    echo "$repo_name"
}

repo_name=$(extract_repo_name)


# Add the remote
git remote add $repo_name $repo_url
echo_blue "${BLUE}add remote git repo${NC}"

# Add the subtree
git subtree add --prefix=$target_dir $repo_name $branch_name
echo_blue "add subtree"

# Add and commit changes
git add -A
git commit -m "add $repo_name"
echo_blue "commit"

# Remove the remote
git remote remove $repo_name
echo_blue "remove git repo for subtree"
