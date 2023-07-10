#!/bin/bash

# Colors
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[0;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color


echo_blue() {
    echo -e "\n${BLUE}$1${NC}"
}


echo_red() {
    echo -e "\n${RED}$1${NC}"
}
