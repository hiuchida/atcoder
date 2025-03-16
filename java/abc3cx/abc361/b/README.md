# Main
考えられる組み合わせを条件分岐した。
		if (a<g&&g<d) b1=true;
		if (a<j&&j<d) b1=true;
		if (g<a&&a<j) b1=true;
		if (g<d&&d<j) b1=true;

# Main\_fix
falseの条件分岐した。
		if (d<=g||j<=a) b1=false;
