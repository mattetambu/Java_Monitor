package Monitor;
		if (p_count <= 0) {
			p_suspended.extract();
			notifyAll();
		}
	/*public synchronized void V () {
		if (!p_suspended.empty()) notifyAll();
		else p_count++;
	}*/
	}
}