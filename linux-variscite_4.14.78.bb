#@DESCRIPTION: Linux for Variscite i.MX boards
#
# http://www.variscite.com

require recipes-kernel/linux/linux-imx.inc

DEPENDS += "lzop-native bc-native"

# Do not copy the kernel image to the rootfs by default
RDEPENDS_${KERNEL_PACKAGE_NAME}-base = ""

LOCALVERSION_var-som-mx6 = "-mx6"
LOCALVERSION_imx6ul-var-dart = "-mx6ul"
LOCALVERSION_imx7-var-som = "-mx7"

#Updated to CD's repo
SRCBRANCH = "master"
SRCREV = "${AUTOREV}"
KERNEL_SRC ?= "git://github.com/CDuke-Janus/kernel-source.git;protocol=git"
SRC_URI = "${KERNEL_SRC};branch=${SRCBRANCH}"

#Added support for PR service
PV = "1.0+git${SRCPV}"

#Added autoboot for the nocturn and ov5642 camera
KERNEL_MODULE_AUTOLOAD += "ovnocturn_xs_camera ov5642_camera"

DEFAULT_PREFERENCE = "1"

KERNEL_DEFCONFIG = "${S}/arch/arm/configs/imx_v7_var_defconfig"

do_merge_delta_config[dirs] = "${B}"

do_merge_delta_config() {
    # allow getting KERNEL_DEFCONFIG from outside of the kernel source tree
    cp ${KERNEL_DEFCONFIG} ${S}/arch/${ARCH}/configs/yocto_defconfig

    # create .config with make config
    oe_runmake -C ${S} O=${B} yocto_defconfig

    # add config fragments
    for deltacfg in ${DELTA_KERNEL_DEFCONFIG}; do
        if [ -f "${S}/arch/${ARCH}/configs/${deltacfg}" ]; then
            oe_runmake -C ${S} O=${B} ${deltacfg}
        elif [ -f "${WORKDIR}/${deltacfg}" ]; then
            ${S}/scripts/kconfig/merge_config.sh -m .config ${WORKDIR}/${deltacfg}
        elif [ -f "${deltacfg}" ]; then
            ${S}/scripts/kconfig/merge_config.sh -m .config ${deltacfg}
        fi
    done
    cp .config ${WORKDIR}/defconfig
}
addtask merge_delta_config before do_preconfigure after do_patch

COMPATIBLE_MACHINE = "(var-som-mx6|imx6ul-var-dart|imx7-var-som)"
