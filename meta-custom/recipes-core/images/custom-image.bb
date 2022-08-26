SUMMARY = "A small image just capable of allowing a device to boot."

IMAGE_INSTALL = "packagegroup-core-boot ${CORE_IMAGE_EXTRA_INSTALL}"

IMAGE_LINGUAS = "en-us"

LICENSE = "MIT"

inherit core-image

# Set rootfs to 512 MiB by default
IMAGE_OVERHEAD_FACTOR ?= "1.0"
IMAGE_ROOTFS_SIZE ?= "524288"

# Adding libraries in to the custom yocto image

IMAGE_INSTALL_append += "util-linux pciutils usbutils python3 python3-pip"
IMAGE_INSTALL_append += "hello-world"
IMAGE_INSTALL_append += "hello-mod module-init-tools"

# Adding Bluetooth Support 
IMAGE_INSTALL_append = " \
        dbus \
        bluez5 \
        packagegroup-tools-bluetooth \
        expat \
"
DISTRO_FEATURES_append = " \
       bluetooth \ 
       "


