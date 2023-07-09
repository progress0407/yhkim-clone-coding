#!/bin/bash

extract_repo_name() {
	local url="https://github.com/progress0407/yhkim-mvc2-form.git"
	local repo_name_with_extension=$(basename "$url")
	local repo_name=${repo_name_with_extension%.git}
	echo "$repo_name"
}

repo_name=$(extract_repo_name)

echo "$repo_name"
