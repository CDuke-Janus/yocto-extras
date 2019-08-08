#!/bin/bash
echo "Removing previous image"
rm -rf ~/fsl-image-qt5-var-som-mx6.wic.gz 
echo "Grabbing image"
sshpass -p "password_here" scp -r username@host:~/var-fslc-yocto/build_fb/tmp/deploy/images/var-som-mx6/fsl-image-qt5-var-som-mx6.wic.gz ~/fsl-image-qt5-var-som-mx6.wic.gz
echo "Burning image"
# Make sure to format the sd card using gparted before running
zcat ~/fsl-image-qt5-var-som-mx6.wic.gz | sudo dd of=/dev/sdc bs=1M && sync
