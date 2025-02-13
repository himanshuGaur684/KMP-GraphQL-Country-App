import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init(){
        SetupKoinKt.doInitKoin()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
