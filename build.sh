#!/bin/bash
echo "Building image"
cd ~/var-fslc-yocto
MACHINE=var-som-mx6 DISTRO=fslc-framebuffer . setup-environment build_fb
bitbake fsl-image-qt5
