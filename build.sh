#!/bin/bash
echo "Building image"
cd ~/var-fslc-yocto
MACHINE=var-som-mx6 DISTRO=fslc-framebuffer . setup-environment build_fb
bitbake fsl-image-qt5
zcat ~/var-fslc-yocto/build_fb/tmp/deploy/images/var-som-mx6/fsl-image-qt5-var-som-mx6.wic.gz > ~/var-fslc-yocto/build_fb/tmp/deploy/images/var-som-mx6/fsl-image-qt5-var-som-mx6.wic
