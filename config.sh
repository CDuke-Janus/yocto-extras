#!/bin/bash
echo "Building config menu"
cd ~/var-fslc-yocto
MACHINE=var-som-mx6 DISTRO=fslc-framebuffer . setup-environment build_fb
bitbake -c menuconfig virtual/kernel
