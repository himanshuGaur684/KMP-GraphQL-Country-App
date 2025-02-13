import SwiftUI
import shared
import KMPObservableViewModelSwiftUI

struct ContentView: View {
	let greet = Greeting().greet()
    
    @StateViewModel var viewModel = SharedViewModelModule.shared.getCountriesViewModel()
    

	var body: some View {
        ZStack{
            
            if viewModel.uiState.isLoading {
                ZStack{
                    ProgressView()
                }.frame(maxWidth: .infinity,maxHeight: .infinity)
            }
            
            if !viewModel.uiState.error.isEmpty{
                ZStack{
                    Text(viewModel.uiState.error)
                        
                }.frame(maxWidth:   .infinity,maxHeight: .infinity)
            }
            
            
            if let data = viewModel.uiState.data {
                List{
                    
                    ForEach(data.countries,id: \.self){item in
                        
                        VStack(alignment:.leading){
                            Text("\(item.name) \(item.emoji)")
                                .font(.system(size: 24,weight: .semibold))
                                .padding(.top,8)
                            Text(item.languages.map({$0.name}).joined(separator: ","))
                                .font(.system(size: 16,weight: .medium))
                                .padding(.top,8)
                            
                        }.frame(maxWidth: .infinity, alignment: .leading)
                    }
                    
                }
            }
            
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
