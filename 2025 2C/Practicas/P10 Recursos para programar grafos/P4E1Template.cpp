#include <utility>
#include <vector>
#include <iostream>

using namespace std;

typedef pair<int, int> arista;

int main() {
	int n, m;
	cin >> n >> m;
	// n vértices y m aristas

	for (int i = 0; i < m; i++) {
		int u, v;
		cin >> u >> v;

		// u<->v es una arista
	}

	bool es_bipartito = true;

	if (es_bipartito) {
		cout << "BIPARTITO" << endl;

		cout << "Partición 1:";
		// Imprimir la primera partición
		cout << endl;

		cout << "Partición 2:";
		// Imprimir la segunda partición
		cout << endl;
	}else {
		cout << "NO BIPARTITO" << endl;

		cout << "Ciclo impar:";
		// Imprimir ciclo impar
		cout << endl;
	}
}
