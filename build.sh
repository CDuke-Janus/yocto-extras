#!/bin/bash
echo "#----------------------#"
echo "Navigating to ~/var-fslc-yocto/build_fb/tmp/work-shared/var-som-mx6/kernel-source"
echo "#----------------------#"
cd ~/var-fslc-yocto/build_fb/tmp/work-shared/var-som-mx6/kernel-source
echo "Updating kernel source"
echo "#----------------------#"
git pull
echo "Building image"
echo "#----------------------#"
cd ~/var-fslc-yocto
MACHINE=var-som-mx6 DISTRO=fslc-framebuffer . setup-environment build_fb
bitbake fsl-image-qt5
