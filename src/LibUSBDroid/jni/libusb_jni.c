/******************************************************************************
*
* JNI wrapper for calling the libusb function
*
******************************************************************************/

#include <string.h>
#include <jni.h>
#include <android/log.h>

#include <stdio.h>
#include <wchar.h>

#include <libusb.h>

#define LIBUSB_JNI_TAG    "[LIBUSB-JNI]"

jstring Java_com_wb_libusb_LibUSBDroid_Version( JNIEnv* env, jobject thiz ) {
    __android_log_write(ANDROID_LOG_INFO, LIBUSB_JNI_TAG, "-- LibUsb 1.0 --");
    return (*env)->NewStringUTF(env, "LibUsb 1.0");
}

jint Java_com_wb_libusb_LibUSBDroid_Initialize( JNIEnv* env, jobject thiz) {

    jint ret = 0;

    __android_log_write(ANDROID_LOG_INFO, LIBUSB_JNI_TAG, "-- initializing libusb --");

    ret = libusb_init(NULL);

    return ret;
}

//libusb_device **devs;

void print_devs(libusb_device **devs) {
	libusb_device *dev;
	int i = 0;

	while ((dev = devs[i++]) != NULL) {
		struct libusb_device_descriptor desc;
		int r = libusb_get_device_descriptor(dev, &desc);
		if (r < 0) {
			fprintf(stderr, "failed to get device descriptor");
			return;
		}

		printf("%04x:%04x (bus %d, device %d)\n",
			desc.idVendor, desc.idProduct,
			libusb_get_bus_number(dev), libusb_get_device_address(dev));
	}
}
