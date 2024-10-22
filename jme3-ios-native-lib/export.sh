rm -rf intermediate-builds release
mkdir intermediate-builds release
xcodebuild archive -project jme3-ios-native-lib.xcodeproj -scheme jme3-ios-native-lib -destination generic/platform=iOS -archivePath intermediate-builds/jme3-ios-native-lib_iOS SKIP_INSTALL=NO BUILD_LIBRARY_FOR_DISTRIBUTION=YES
xcodebuild archive -project jme3-ios-native-lib.xcodeproj -scheme jme3-ios-native-lib -destination generic/platform="iOS Simulator" -archivePath intermediate-builds/jme3-ios-native-lib_iOS-Simulator SKIP_INSTALL=NO BUILD_LIBRARY_FOR_DISTRIBUTION=YES

xcodebuild -create-xcframework -framework intermediate-builds/jme3-ios-native-lib_iOS.xcarchive/Products/Library/Frameworks/jme3_ios_native_lib.framework -framework intermediate-builds/jme3-ios-native-lib_iOS-Simulator.xcarchive/Products/Library/Frameworks/jme3_ios_native_lib.framework -output release/jme3-ios-native-lib.xcframework

