# Wallet
In this project, We are trying to solve a simple problem using MVVM. Here, we are getting transactions and then filtering the transaction.

# MVVM - Model. View. ViewModel
The advantage of using MVVM is its ViewModel. The ViewModel retrieves the necessary data from the DataModel, applies the UI logic and then exposes relevant data for the View to consume.

**Model** - represents the data and business logic, exposes data through observables.

**View** - observes viewModel to get the data and updates in UI.

**ViewModel** - interacts with the model and prepares observables.

In our example, **TransactionViewModel** is viewModel which loads the trasaction and filters the transactions based on string which is provided by User.


