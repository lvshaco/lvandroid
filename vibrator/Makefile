.PHONY : default update build pack install dist all clean cleanall push dump

name=vibrator

#ifeq($(name),)
	#@echo please do `make name=project name`
#endif

default: dist
	
update:
	android update project -p . --target android-20
build:
	ndk-build NDK_BUILD=1 #NDK_MODULE_PATH=`pwd`
pack:
	#rm -rf assets/files/*
	#cp -r ../ejoy2d assets/files
	#cp -r ../examples assets/files
	#mkdir -pv assets/files/config
	#echo flappybird > assets/files/config/config
	ant debug
install:
	adb uninstall com.$(name)
	adb install bin/$(name)Activity-debug.apk

dist: update pack install
all: update dist

clean:
	rm -rf bin gen libs obj build.xml
cleanall: clean
	rm -f build.xml proguard-project.txt

push:
	adb push ../ejoy2d /data/data/com.example.ej2dnative/files/ejoy2d
	adb push ../examples /data/data/com.example.ej2dnative/files/examples

dump:
	echo "*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***" > .dump.log
	adb logcat -d -s DEBUG >> .dump.log
	ndk-stack -sym obj/local/armeabi -dump .dump.log
	rm -f .dump.log
