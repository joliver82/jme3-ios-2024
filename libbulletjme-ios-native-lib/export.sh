rm -rf intermediate-builds release template/META-INF/robovm/ios/libs/libbulletjme-ios-native-lib.xcframework
mkdir intermediate-builds release
xcodebuild archive -project libbulletjme-ios-native-lib.xcodeproj -scheme libbulletjme-ios-native-lib -configuration release -destination generic/platform=iOS -archivePath intermediate-builds/libbulletjme-ios-native-lib_iOS SKIP_INSTALL=NO BUILD_LIBRARY_FOR_DISTRIBUTION=YES
xcodebuild archive -project libbulletjme-ios-native-lib.xcodeproj -scheme libbulletjme-ios-native-lib -configuration release -destination generic/platform="iOS Simulator" -archivePath intermediate-builds/libbulletjme-ios-native-lib_iOS-Simulator SKIP_INSTALL=NO BUILD_LIBRARY_FOR_DISTRIBUTION=YES

xcodebuild -create-xcframework -framework intermediate-builds/libbulletjme-ios-native-lib_iOS.xcarchive/Products/Library/Frameworks/libbulletjme_ios_native_lib.framework -framework intermediate-builds/libbulletjme-ios-native-lib_iOS-Simulator.xcarchive/Products/Library/Frameworks/libbulletjme_ios_native_lib.framework -output template/META-INF/robovm/ios/libs/libbulletjme-ios-native-lib.xcframework

cd template
zip -r ../release/libbulletjme-ios-native-lib.jar META-INF
cd ..

