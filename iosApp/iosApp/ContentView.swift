import SwiftUI
import shared
import KMPObservableViewModelSwiftUI

struct ContentView: View {
    
    @StateViewModel var viewModel = CountriesViewModel()
    
	let greet = Greeting().greet()

	var body: some View {
        NavigationStack{
            ZStack{
             
                
                if viewModel.uiState.isLoading  {
                    ZStack{
                        ProgressView("Loading")
                    }.frame(maxWidth: .infinity,maxHeight: .infinity)
                }
                
                
                if let data = viewModel.uiState.data {
                    List{
                        ForEach(data.countries,id:\.self){item in
                        
                            VStack(alignment:.leading){
                                Text(item.name+" \(item.emoji)")
                                    .font(.system(size: 24,weight: .semibold))
                                    .padding(.top,8)
     
                                Text(item.languages.map({ $0.name}).joined(separator: ","))
                                    .font(.system(size: 16))
                                    .padding(.bottom,8)
                            }.frame(maxWidth: .infinity,alignment: .leading)
                            
                            
                        }
                    }.frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/)
                        .navigationTitle("Countries")
                }
                
                if !viewModel.uiState.error.isEmpty {
                    ZStack{
                        Text(viewModel.uiState.error)
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
