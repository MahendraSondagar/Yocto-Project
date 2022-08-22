SUMMARY = "A small image just capable of allowing a device to boot."

IMAGE_INSTALL = "packagegroup-core-boot ${CORE_IMAGE_EXTRA_INSTALL}"

IMAGE_LINGUAS = " "

LICENSE = "MIT"

inherit core-image

# Set rootfs to 200 MiB by default
IMAGE_OVERHEAD_FACTOR ?= "1.0"
IMAGE_ROOTFS_SIZE ?= "204800"

# Adding libraries in to the custom yocto image

IMAGE_INSTALL_append += "util-linux pciutils usbutils python3 python3-pip"
IMAGE_INSTALL_append += "hello-world"
IMAGE_INSTALL_append += "hello-mod module-init-tools"

# Add kernel module for USB WiFi driver
IMAGE_INSTALL  = "kernel-module-r8188eu \
                  linux-firmware-rtl8188 \
                  dhcp-client \
                  iw \
                  wpa-supplicant \
                  wireless-regdb-static"

# Autoload WiFi driver on boot
KERNEL_MODULE_AUTOLOAD += "r8188eu"


# Bluetooth subsystem configuration

IMAGE_INSTALL += "\
        dbus \
        bluez5 \
        packagegroup-tools-bluetooth \
        expat \
"

IMAGE_INSTALL_append += "obexftp obex-data-server python3-dbus python3-pygobject python3-pybluez"

DISTRO_FEATURES_append = " \
       bluetooth \ 
       "

# Enable systemd in the image for the Bluetooth services :) 

DISTRO_FEATURES_append = " systemd"
DISTRO_FEATURES_BACKFILL_CONSIDERED += "sysvinit"
VIRTUAL-RUNTIME_init_manager = "systemd"
VIRTUAL-RUNTIME_initscripts = "systemd-compat-units"
