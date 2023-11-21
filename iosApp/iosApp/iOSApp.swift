import SwiftUI
//import FirebaseCore
import ComposeApp

@main
struct iOSApp: App {
    
    @UIApplicationDelegateAdaptor(AppDelegate.self) var delegate
    init() {
           HelperKt.doInitKoin()
       }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}

class AppDelegate: NSObject, UIApplicationDelegate {
  func application(_ application: UIApplication,
                   didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
//    FirebaseApp.configure()

    return true
  }
}
