require 'json'

package = JSON.parse(File.read(File.join(__dir__, 'package.json')))

Pod::Spec.new do |s|
  s.name         = "ReactNativeNavigation"
  s.version      = package['version']
  s.summary      = package['description']

  s.authors      = "Wix.com"
  s.homepage     = package['homepage']
  s.license      = package['license']
  s.platform     = :ios, "9.0"

  s.module_name  = 'ReactNativeNavigation'

  s.source       = { :git => "https://github.com/DaBs/react-native-navigation.git" }
  s.source_files  = "ios/**/*.{h,m}"
  s.exclude_files  = "ios/ReactNativeNavigationTests/**/*.*"

  s.dependency 'React'
  s.frameworks = 'UIKit'
end
